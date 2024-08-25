def count_dice_ways(target_sum):
    if target_sum < 2 or target_sum > 12:
        return 0
    
    count = 0
    for dice1 in range(1, 7):
        for dice2 in range(1, 7):
            if dice1 + dice2 == target_sum:
                count += 1
                
    return count

# Example usage
target_number = int(input("Enter a number: "))
result = count_dice_ways(target_number)
print(f"Number of ways to express {target_number} as sum of two dice faces: {result}")
