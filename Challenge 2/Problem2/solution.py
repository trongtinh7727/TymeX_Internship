def solution(arr,n):
    return sum(range(n)) - sum(arr)

n = 7
arr = [1,2,3,5,6]
print(solution(arr,n))
