from constraint import *


def two(simona, marija, petar):
    if simona == 1:
        if marija == 1 or petar == 1:
            return True
    return False


def valid_time(time, marija, petar):
    simona_domain = [13, 14, 16, 19]
    marija_domain = [14, 15, 18]
    petar_domain = [12, 13, 16, 17, 18, 19]
    if time in simona_domain:
        if marija == 1 and petar == 1:
            if time in marija_domain and time in petar_domain:
                return True
            else:
                return False
        elif marija == 1:
            if time in marija_domain:
                return True
            else:
                return False
        elif petar == 1:
            if time in petar_domain:
                return True
            else:
                return False
    return False


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())

    # ---Dadeni se promenlivite, dodadete gi domenite-----
    problem.addVariable("Marija_prisustvo", [1, 0])
    problem.addVariable("Simona_prisustvo", [1])
    problem.addVariable("Petar_prisustvo", [1, 0])
    problem.addVariable("vreme_sostanok", [i for i in range(12, 20)])
    # ----------------------------------------------------

    # ---Tuka dodadete gi ogranichuvanjata----------------

    problem.addConstraint(two, ['Simona_prisustvo', 'Marija_prisustvo', 'Petar_prisustvo'])
    problem.addConstraint(valid_time, ['vreme_sostanok', 'Marija_prisustvo', 'Petar_prisustvo'])
    # ----------------------------------------------------

    [print(solution) for solution in problem.getSolutions()]
