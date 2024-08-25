def smallest_lexicographic_string(S, N, A, M):
    if N % 5 != 0:
        return "EMPTY"
    
    # Step 1: Count occurrences of each 5-bit pattern in S
    pattern_count = {}
    for i in range(0, N, 5):
        bin_str = S[i:i+5]
        if bin_str in pattern_count:
            pattern_count[bin_str] += 1
        else:
            pattern_count[bin_str] = 1
    
    # Step 2: Create a mapping from binary string to character
    bin_to_char = {A[i]: chr(97 + i) for i in range(M)}
    
    # Step 3: Count how many times each character can be formed
    char_count = {}
    
    for bin_str, count in pattern_count.items():
        if bin_str in bin_to_char:
            char = bin_to_char[bin_str]
            if char in char_count:
                char_count[char] += count
            else:
                char_count[char] = count
    
    # Step 4: Construct the smallest lexicographical string
    result = []
    for char in sorted(char_count.keys()):
        result.append(char * char_count[char])
    
    return ''.join(result) if result else "EMPTY"

# Example usage
S = "0000100101"
N = 10
A = ["00000", "00001", "00010", "00011"]
M = 4

print(smallest_lexicographic_string(S, N, A, M))  # Expected output: "ab"
