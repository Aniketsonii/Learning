# Python3 program to print Kth character
# in sorted concatenated substrings

# Structure to store information of a suffix
class suffix:
	
	def __init__(self):
		
		self.index = 0
		
		# To store original index
		self.rank = [0] * 2
		
		# To store ranks and next
		# rank pair

# This is the main function that takes a string
# 'txt' of size n as an argument, builds and return
# the suffix array for the given string
def buildSuffixArray(txt: str, n: int) -> list:

	# A structure to store suffixes
	# and their indexes
	suffixes = [0] * n
	for i in range(n):
		suffixes[i] = suffix()

	# Store suffixes and their indexes in an array
	# of structures. The structure is needed to sort
	# the suffixes alphabetically and maintain their
	# old indexes while sorting
	for i in range(n):
		suffixes[i].index = i
		suffixes[i].rank[0] = ord(txt[i]) - ord('a')
		suffixes[i].rank[1] = (ord(txt[i + 1]) -
						ord('a')) if ((i + 1) < n) else -1

	# Sort the suffixes using the comparison function
	# defined above.
	suffixes.sort(key = lambda a: a.rank)

	# At his point, all suffixes are sorted according
	# to first 2 characters. Let us sort suffixes
	# according to first 4 characters, then first
	# 8 and so on
	ind = [0] * n
	
	# This array is needed to get the
	# index in suffixes[] from original
	# index. This mapping is needed to get
	# next suffix.
	k = 4
	while k < 2 * n:
		k *= 2
		
		# for k in range(4, 2 * n, k * 2):

		# Assigning rank and index values
		# to first suffix
		rank = 0
		prev_rank = suffixes[0].rank[0]
		suffixes[0].rank[0] = rank
		ind[suffixes[0].index] = 0

		# Assigning rank to suffixes
		for i in range(1, n):

			# If first rank and next ranks are same as
			# that of previous suffix in array, assign
			# the same new rank to this suffix
			if (suffixes[i].rank[0] == prev_rank and
				suffixes[i].rank[1] == suffixes[i - 1].rank[1]):
				prev_rank = suffixes[i].rank[0]
				suffixes[i].rank[0] = rank
				
			# Otherwise increment rank and assign
			else:
				prev_rank = suffixes[i].rank[0]
				rank += 1
				suffixes[i].rank[0] = rank

			ind[suffixes[i].index] = i

		# Assign next rank to every suffix
		for i in range(n):
			nextindex = suffixes[i].index + k // 2
			suffixes[i].rank[1] = suffixes[ind[nextindex]].rank[0] if (
				nextindex < n) else -1

		# Sort the suffixes according to first k characters
		suffixes.sort(key = lambda a : a.rank)

	# Store indexes of all sorted suffixes
	# in the suffix array
	suffixArr = []
	for i in range(n):
		suffixArr.append(suffixes[i].index)

	# Return the suffix array
	return suffixArr

# To construct and return LCP */
def kasai(txt: str, suffixArr: list) -> list:

	n = len(suffixArr)

	# To store LCP array
	lcp = [0] * n

	# An auxiliary array to store inverse of
	# suffix array elements. For example if
	# suffixArr[0] is 5, the invSuff[5] would
	# store 0. This is used to get next
	# suffix string from suffix array.
	invSuff = [0] * n

	# Fill values in invSuff[]
	for i in range(n):
		invSuff[suffixArr[i]] = i

	# Initialize length of previous LCP
	k = 0

	# Process all suffixes one by one
	# starting from first suffix in txt[]
	for i in range(n):

		# If the current suffix is at n-1, then
		# we don’t have next substring to
		# consider. So lcp is not defined for
		# this substring, we put zero.
		if (invSuff[i] == n - 1):
			k = 0
			continue

		# j contains index of the next substring to
		# be considered to compare with the present
		# substring, i.e., next string in suffix array
		j = suffixArr[invSuff[i] + 1]

		# Directly start matching from k'th index as
		# at-least k-1 characters will match
		while (i + k < n and j + k < n and
		txt[i + k] == txt[j + k]):
			k += 1

		lcp[invSuff[i]] = k
		# lcp for the present suffix.

		# Deleting the starting character
		# from the string.
		if (k > 0):
			k -= 1

	# Return the constructed lcp array
	return lcp

# Utility method to get sum of first N numbers
def sumOfFirstN(N: int) -> int:

	return (N * (N + 1)) // 2

# Returns Kth character in sorted concatenated
# substrings of str
def printKthCharInConcatSubstring(string: str,
							K: int) -> str:

	n = len(string)
	
	# Calculating suffix array and lcp array
	suffixArr = buildSuffixArray(string, n)
	lcp = kasai(string, suffixArr)

	for i in range(len(lcp)):

		# Skipping characters common to substring
		# (n - suffixArr[i]) is length of current
		# maximum substring lcp[i] will length of
		# common substring
		charToSkip = (sumOfFirstN(n - suffixArr[i]) -
								sumOfFirstN(lcp[i]))

		# If characters are more than K, that means
		# Kth character belongs to substring
		# corresponding to current lcp[i]
		if (K <= charToSkip):

			# Loop from current lcp value to current
			# string length
			for j in range(lcp[i] + 1,
			(n - suffixArr[i]) + 1):
				curSubstringLen = j

				# Again reduce K by current substring's
				# length one by one and when it becomes less,
				# print Kth character of current substring
				if (K <= curSubstringLen):
					return string[(suffixArr[i] + K - 1)]
				else:
					K -= curSubstringLen
					
			break

		else:
			K -= charToSkip

# Driver code
if __name__ == "__main__":

	string = "banana"
	K = 10
	
	print(printKthCharInConcatSubstring(string, K))

# This code is contributed by sanjeev2552
