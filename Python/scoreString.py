def scoreString(str):
    def is_palindrome(sub):
        return sub == sub[::-1]

    score = 0

    for length in [4, 5]:
        for i in range(len(s) - length + 1):
            substring = s[i:i + length]
            if is_palindrome(substring):
                if length == 4:
                    score += 5
                elif length == 5:
                    score += 10

    return score


# Example usage
input_str = input()
score = scoreString(input_str)
print(f"Score: {score}")
