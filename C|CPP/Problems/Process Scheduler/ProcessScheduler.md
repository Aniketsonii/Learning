
# Process Scheduler

This project simulates a basic process scheduler that manages the execution of diverse processes, including interactive and real-time tasks, each with unique resource demands such as CPU, TTY, and Disk. The scheduler reads input from a file named `Input.txt` and generates a summary report in `Output.txt` after the simulation is completed.

## Table of Contents

- [Compilation and Execution](#compilation-and-execution)
- [Input Format](#input-format)
- [Input Definitions](#input-definitions)
- [Output Format](#output-format)
- [Example](#example)
- [License](#license)

## Compilation and Execution

To compile and execute the program, follow these steps:

1. **Compile the program:**
   \`\`\`bash
   gcc Operatingsys.c
   \`\`\`

2. **Execute the program:**
   \`\`\`bash
   ./a.out < input.txt   # Linux/Unix/MacOS
   \`\`\`

   or

   \`\`\`bash
   .\a.exe input.txt output.txt   # Windows
   \`\`\`

## Input Format

The input file `Input.txt` should adhere to the following format:

\`\`\`
<Process_Class> <Arrival_Time>
DEADLINE <Deadline>
<Resource_Type> <Duration>
...
\`\`\`

## Input Definitions

- `<Process_Class>`: Indicates the type of process. It can either be `INTERACTIVE` or `REAL-TIME`.
- `<Arrival_Time>`: Specifies the time when the process arrives.
- `DEADLINE`: A keyword used to mark the deadline for a real-time process.
- `<Deadline>`: The deadline for real-time processes.
- `<Resource_Type>`: Specifies the resource required by the process. It can be `CPU`, `TTY`, or `DISK`.
- `<Duration>`: The duration of the resource request in milliseconds.

## Output Format

The program generates an output file named `Output.txt`, containing a summary report of the simulation. The report includes the following:

- Number of real-time processes completed.
- Percentage of real-time processes that missed their deadline.
- Number of interactive processes completed.
- Total number of disk accesses.
- Average duration of disk access.
- Total time elapsed since the start of the first process.
- CPU utilization.
- Disk utilization.

## Example

### Input Example (`Input.txt`):
\`\`\`
REAL-TIME 10
DEADLINE 100
CPU 50
DISK 200

INTERACTIVE 20
TTY 100
CPU 50
\`\`\`

### Output Example (`Output.txt`):
\`\`\`
Number of real-time processes completed: 1
Percentage of real-time processes that missed their deadline: 0%
Number of interactive processes completed: 1
Total number of disk accesses: 1
Average duration of disk access: 200 ms
Total time elapsed since the start of the first process: 300 ms
CPU utilization: 33%
Disk utilization: 66%
\`\`\`

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
