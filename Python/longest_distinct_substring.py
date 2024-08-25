def longest_distinct_substring(s):
    seen = {}  # To store the most recent index of each alphabet
    start = 0  # Starting index of current substring
    max_length = 0  # Length of the longest substring with distinct alphabets

    for end, char in enumerate(s):
        if char in seen and seen[char] >= start:
            start = seen[char] + 1
        seen[char] = end
        max_length = max(max_length, end - start + 1)

    return max_length

# Example usage
input_string = input("enter string")
result = longest_distinct_substring(input_string)
print("Longest substring with distinct alphabets:", result)