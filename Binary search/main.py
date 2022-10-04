''' binary search in python '''


def binarySearch (arr, l, r, x):
    
    if r >= l:
        mid = l + (r - l) // 2
        if arr[mid] == x:
            return mid

        elif arr[mid] > x:
            return binarySearch(arr, l, mid-1, x)

        else:
            return binarySearch(arr, mid + 1, r, x)
  
    else:

        return -1
  

arr = [ 23, 44, 27, 10, 0 ,26]
x = 24
  

result = binarySearch(arr, 0, len(arr)-1, x)
  
if result != -1:
    print ("number is present at index % d" % result)
else:
    print ("number is not present in array")