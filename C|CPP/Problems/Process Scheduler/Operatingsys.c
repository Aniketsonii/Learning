#include <unistd.h> // For usleep
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/time.h>

#define MAX_LINE_LENGTH 100
#define MAX_PROCESSES 100

// Define process class types
typedef enum {
    INTERACTIVE,
    REAL_TIME
} ProcessClass;

// Define process status types
typedef enum {
    READY,
    RUNNING,
    WAITING
} ProcessStatus;

// Define resource types
typedef enum {
    CPU,
    TTY,
    DISK
} ResourceType;

// Define a structure to represent a resource request
typedef struct {
    ResourceType type;
    int duration;
} ResourceRequest;

// Define a structure to represent a process
typedef struct {
    int sequence_number;
    ProcessClass class;
    int arrival_time;
    ProcessStatus status;
    ResourceRequest* requests;
    int num_requests;
    int deadline;
    long long start_time_ms;
    long long end_time_ms;
    int completed;
} Process;

// Define a structure for a node in a queue
typedef struct Node {
    Process* data;
    struct Node* next;
} Node;

// Define a structure for a queue
typedef struct Queue {
    Node* front;
    Node* rear;
} Queue;

FILE *output_file;

// Function prototypes
Process* create_process(int seq_num, ProcessClass class, int arrival_time, ResourceRequest requests[], int num_requests, int deadline);
Node* create_node(Process* process);
Queue* create_queue();
void enqueue(Queue* queue, Process* process);
Process* dequeue(Queue* queue);
long long current_time_in_ms();
void print_summary(int real_time_completed, float real_time_missed_percentage, int interactive_completed, int total_disk_accesses, float avg_disk_access_duration, long long total_time_elapsed, float cpu_utilization, float disk_utilization);
void simulate_processes(Process* processes[], int num_processes);
void preempt_process(Queue* queue);
void read_input(const char* filename, Process* processes[], int* num_processes);

// Function to create a new process
Process* create_process(int seq_num, ProcessClass class, int arrival_time, ResourceRequest requests[], int num_requests, int deadline) {
    Process* new_process = (Process*)malloc(sizeof(Process));
    new_process->sequence_number = seq_num;
    new_process->class = class;
    new_process->arrival_time = arrival_time;
    new_process->status = READY;
    new_process->requests = (ResourceRequest*)malloc(num_requests * sizeof(ResourceRequest));
    memcpy(new_process->requests, requests, num_requests * sizeof(ResourceRequest));
    new_process->num_requests = num_requests;
    new_process->deadline = deadline;
    new_process->start_time_ms = 0;
    new_process->end_time_ms = 0;
    new_process->completed = 0;
    return new_process;
}

// Function to create a new node for a queue
Node* create_node(Process* process) {
    Node* new_node = (Node*)malloc(sizeof(Node));
    new_node->data = process;
    new_node->next = NULL;
    return new_node;
}

// Function to create a new queue
Queue* create_queue() {
    Queue* queue = (Queue*)malloc(sizeof(Queue));
    queue->front = queue->rear = NULL;
    return queue;
}

// Function to enqueue a process into a queue
void enqueue(Queue* queue, Process* process) {
    Node* new_node = create_node(process);
    if (queue->rear == NULL) {
        queue->front = queue->rear = new_node;
    } else {
        queue->rear->next = new_node;
        queue->rear = new_node;
    }
}

// Function to dequeue a process from a queue
Process* dequeue(Queue* queue) {
    if (queue->front == NULL) {
        return NULL;
    }
    Node* temp = queue->front;
    Process* process = temp->data;
    queue->front = queue->front->next;
    if (queue->front == NULL) {
        queue->rear = NULL;
    }
    free(temp);
    return process;
}

// Function to get the current time in milliseconds
long long current_time_in_ms() {
    struct timeval tv;
    gettimeofday(&tv, NULL);
    return ((long long)tv.tv_sec) * 1000 + ((long long)tv.tv_usec) / 1000;
}

// Function to print the summary report
void print_summary(int real_time_completed, float real_time_missed_percentage, int interactive_completed, int total_disk_accesses, float avg_disk_access_duration, long long total_time_elapsed, float cpu_utilization, float disk_utilization) {
    printf("\nSummary Report:\n");
    printf("a) Real-time processes completed: %d\n", real_time_completed);
    printf("b) Percentage of real-time processes that missed their deadline: %.2f%%\n", real_time_missed_percentage);
    printf("c) Interactive processes completed: %d\n", interactive_completed);
    printf("d) Total number of disk accesses: %d\n", total_disk_accesses);
    printf("e) Average duration of a disk access: %.2f ms\n", avg_disk_access_duration);
    printf("f) Total time elapsed since the start of the first process: %lld ms\n", total_time_elapsed);
    printf("g) CPU utilization: %.2f%%\n", cpu_utilization);
    printf("h) Disk utilization: %.2f%%\n", disk_utilization);
    fprintf(output_file,"\nSummary Report:\n");
    fprintf(output_file,"a) Real-time processes completed: %d\n", real_time_completed);
    fprintf(output_file,"b) Percentage of real-time processes that missed their deadline: %.2f%%\n", real_time_missed_percentage);
    fprintf(output_file,"c) Interactive processes completed: %d\n", interactive_completed);
    fprintf(output_file,"d) Total number of disk accesses: %d\n", total_disk_accesses);
    fprintf(output_file,"e) Average duration of a disk access: %.2f ms\n", avg_disk_access_duration);
    fprintf(output_file,"f) Total time elapsed since the start of the first process: %lld ms\n", total_time_elapsed);
    fprintf(output_file,"g) CPU utilization: %.2f%%\n", cpu_utilization);
    fprintf(output_file,"h) Disk utilization: %.2f%%\n", disk_utilization);
}

// Function to preempt the current running process
void preempt_process(Queue* queue) {
    if (queue->front != NULL) {
        Process* preemted_process = dequeue(queue);
        preemted_process->status = READY;
        enqueue(queue, preemted_process);
        printf("[%lldms] CPU preempted from Process %d.\n", current_time_in_ms(), preemted_process->sequence_number);
        fprintf(output_file,"[%lldms] CPU preempted from Process %d.\n", current_time_in_ms(), preemted_process->sequence_number);
    }
}

// Function to read input from file
// Function to read input from file
void read_input(const char* filename, Process* processes[], int* num_processes) {
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        printf("Error: Unable to open file %s\n", filename);
        exit(1);
    }

    char line[MAX_LINE_LENGTH];
    int process_index = 0;
    ProcessClass current_class = INTERACTIVE;  // Start with INTERACTIVE class
    int current_arrival_time = 0;
    int current_deadline = 0;
    ResourceRequest requests[MAX_LINE_LENGTH]; // Temporary storage for resource requests
    int num_requests = 0;

    while (fgets(line, MAX_LINE_LENGTH, file) != NULL) {
        char* token = strtok(line, " \n"); // Tokenize line by space or newline
        if (strcmp(token, "INTERACTIVE") == 0 || strcmp(token, "REAL-TIME") == 0) {
            // Process class line
            if (num_requests > 0) {
                // Create process with the collected data
                processes[process_index] = create_process(process_index + 1, current_class, current_arrival_time, requests, num_requests, current_deadline);
                process_index++;
                // Reset request data for the next process
                num_requests = 0;
            }
            if (strcmp(token, "INTERACTIVE") == 0) {
                current_class = INTERACTIVE;
            } else {
                current_class = REAL_TIME;
            }
            // Get arrival time
            token = strtok(NULL, " \n");
            current_arrival_time = atoi(token);
        } else if (strcmp(token, "DEADLINE") == 0) {
            // Deadline line
            token = strtok(NULL, " \n");
            current_deadline = atoi(token);
        } else {
            // Resource request line
            ResourceType type;
            if (strcmp(token, "CPU") == 0) {
                type = CPU;
            } else if (strcmp(token, "TTY") == 0) {
                type = TTY;
            } else if (strcmp(token, "DISK") == 0) {
                type = DISK;
            } else {
                fprintf(stderr, "Error: Invalid resource type\n");
                exit(1);
            }
            // Get duration
            token = strtok(NULL, " \n");
            int duration = atoi(token);
            // Store the request
            requests[num_requests].type = type;
            requests[num_requests].duration = duration;
            num_requests++;
        }
    }
    // Create process for the last set of requests
    if (num_requests > 0) {
        processes[process_index] = create_process(process_index + 1, current_class, current_arrival_time, requests, num_requests, current_deadline);
        process_index++;
    }
    *num_processes = process_index;
    fclose(file);
}

// Function to simulate the execution of processes
void simulate_processes(Process* processes[], int num_processes) {
    Queue* real_time_queue = create_queue();
    Queue* interactive_queue = create_queue();
    Queue* disk_queue = create_queue();

    int real_time_completed = 0;
    int real_time_missed_deadline = 0;
    int interactive_completed = 0;
    int total_disk_accesses = 0;
    long long total_time_elapsed = 0;

    float total_cpu_busy_time = 0;
    float total_disk_busy_time = 0;
    long long disk_queue_start_time = 0;

    long long start_time = current_time_in_ms();
    int cpu_allocated = 0;

    // Main simulation loop
    for (int i = 0; i < num_processes; i++) {
        Process* current_process = processes[i];

        // Simulate process arrival time
        while (current_time_in_ms() < start_time + current_process->arrival_time) {
            usleep(1000);
        }

        current_process->start_time_ms = current_time_in_ms();
        printf("[%lldms] Process %d (%s) started.\n", current_process->start_time_ms - start_time, current_process->sequence_number, current_process->class == INTERACTIVE ? "INTERACTIVE" : "REAL-TIME");
        fprintf(output_file,"[%lldms] Process %d (%s) started.\n", current_process->start_time_ms - start_time, current_process->sequence_number, current_process->class == INTERACTIVE ? "INTERACTIVE" : "REAL-TIME");

        // Process resource requests
        for (int j = 0; j < current_process->num_requests; j++) {
            ResourceRequest request = current_process->requests[j];
            usleep(request.duration * 1000);

            switch (request.type) {
                case CPU:
                    if (current_process->class == REAL_TIME) {
                        if (!cpu_allocated || interactive_queue->front != NULL) {
                            if (cpu_allocated) {
                                preempt_process(interactive_queue);
                            }
                            printf("[%lldms] CPU allocated to Real-time Process %d.\n", current_time_in_ms() - start_time, current_process->sequence_number);
                            fprintf(output_file,"[%lldms] CPU allocated to Real-time Process %d.\n", current_time_in_ms() - start_time, current_process->sequence_number);
                            cpu_allocated = 1;
                        }
                    } else if (current_process->class == INTERACTIVE && cpu_allocated) {
                        printf("[%lldms] CPU preempted from Interactive Process %d.\n", current_time_in_ms() - start_time, current_process->sequence_number);
                        fprintf(output_file,"[%lldms] CPU preempted from Interactive Process %d.\n", current_time_in_ms() - start_time, current_process->sequence_number);
                        cpu_allocated = 0;
                    }
                    total_cpu_busy_time += request.duration;
                    break;
                case TTY:
                    break;
                case DISK:
                    if (disk_queue_start_time == 0) {
                        disk_queue_start_time = current_time_in_ms();
                    }
                    enqueue(disk_queue, current_process);
                    total_disk_accesses++;
                    break;
            }
        }

        current_process->end_time_ms = current_time_in_ms();
        printf("[%lldms] Process %d (%s) completed in %lldms.\n", current_process->end_time_ms - start_time, current_process->sequence_number, current_process->class == INTERACTIVE ? "INTERACTIVE" : "REAL-TIME", current_process->end_time_ms - current_process->start_time_ms);
        fprintf(output_file,"[%lldms] Process %d (%s) completed in %lldms.\n", current_process->end_time_ms - start_time, current_process->sequence_number, current_process->class == INTERACTIVE ? "INTERACTIVE" : "REAL-TIME", current_process->end_time_ms - current_process->start_time_ms);
        total_time_elapsed = current_process->end_time_ms - start_time;
        if (current_process->class == REAL_TIME && current_process->end_time_ms > start_time + current_process->deadline) {
            printf("[%lldms] Real-time Process %d missed its deadline!\n", current_process->end_time_ms - start_time, current_process->sequence_number);
            fprintf(output_file,"[%lldms] Real-time Process %d missed its deadline!\n", current_process->end_time_ms - start_time, current_process->sequence_number);
            real_time_missed_deadline++;
        }
        if (current_process->class == INTERACTIVE) {
            interactive_completed++;
        } else {
            real_time_completed++;
        }
    }

    if (disk_queue_start_time > 0) {
        total_disk_busy_time += current_time_in_ms() - disk_queue_start_time;
    }

    float real_time_missed_percentage = (float)real_time_missed_deadline / real_time_completed * 100;
    float avg_disk_access_duration = total_disk_busy_time / total_disk_accesses;
    float cpu_utilization = (total_cpu_busy_time / total_time_elapsed) * 100;
    float disk_utilization = ((float)total_disk_busy_time / total_time_elapsed) * 100;

    // Print summary report
    print_summary(real_time_completed, real_time_missed_percentage, interactive_completed, total_disk_accesses, avg_disk_access_duration, total_time_elapsed, cpu_utilization, disk_utilization);
}

int main() {
    output_file = fopen("output.txt", "w");
    if (output_file == NULL)
    {
        printf("Error opening output file.\n");
    }
    Process* processes[MAX_PROCESSES];
    int num_processes;

    // Read input from file
    read_input("input.txt", processes, &num_processes);

    // Simulate processes
    simulate_processes(processes, num_processes);

    fclose(output_file);
    return 0;
}
