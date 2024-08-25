def max_amplifiers(M, A, S):
    if S == 0:
        return -1  # Cannot achieve signal strength 0 starting from 1
    
    if S == 1:
        return 0  # No amplifiers needed to achieve signal strength 1

    max_amplifiers_used = -1

    # Traverse each possible starting point
    for start in range(M):
        product = 1
        
        # Check each possible subarray starting from `start`
        for end in range(start, M):
            if A[end] == 0:
                product = 1  # Reset product if we hit a zero
                break
            product *= A[end]
            
            if product == S:
                max_amplifiers_used = max(max_amplifiers_used, end - start + 1)
            elif product > S:
                break  # Stop early if product exceeds S

    return max_amplifiers_used

# Example usage
M = 5
A = [1,2,3,4,6]
S = 12
print(max_amplifiers(M, A, S))  # Expected output: -1 (since no contiguous subarray multiplies exactly to 12)
