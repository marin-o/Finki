from constraint import *


def queensTest(q1, q2):
    x, y = q1
    if q1[0] == q2[0] or q1[1] == q2[1]:
        return False

    while x >= 0 and y >= 0:
        if x == q2[0] and y == q2[1]:
            return False
        x -= 1
        y -= 1

    x, y = q1
    while x >= 0 and y < n:
        if x == q2[0] and y == q2[1]:
            return False
        x -= 1
        y += 1

    x, y = q1
    while x < n and y >= 0:
        if x == q2[0] and y == q2[1]:
            return False
        x += 1
        y -= 1

    x, y = q1
    while x < n and y < n:
        if x == q2[0] and y == q2[1]:
            return False
        x += 1
        y += 1

    return True


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    n = int(input())
    count = 0
    domain = [(i, j) for i in range(0, n) for j in range(0, n)]

    queens = range(1, n + 1)

    problem.addVariables(queens, domain)

    # ---Tuka dodadete gi ogranichuvanjata----------------
    for queen1 in queens:
        for queen2 in queens:
            if queen1 < queen2:
                problem.addConstraint(queensTest, (queen1, queen2))

    # ----------------------------------------------------
    if n <= 6:
        print(len(problem.getSolutions()))
    else:
        print(problem.getSolution())

