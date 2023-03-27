from constraint import *


def odgovaraZaSite(simona, marija, petar, vremeto):
    if marija == 1 and vremeto not in [14, 15, 18]:
        return False
    if petar == 1 and vremeto not in [12, 13, 16, 17, 18, 19]:
        return False
    if simona == 1 and vremeto not in [13, 14, 16, 19]:
        return False
    return True


def simonaPrisutna(simona):
    return simona == 1


def vremeVoRed(vreme):
    return vreme in [13, 14, 16, 19]


def simonaIUsteEden(simona, marija, petar):
    return simona == 1 and (marija == 1 or petar == 1)


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())

    # ---Dadeni se promenlivite, dodadete gi domenite-----
    problem.addVariable("Simona_prisustvo", [0, 1])
    problem.addVariable("Marija_prisustvo", [0, 1])
    problem.addVariable("Petar_prisustvo", [0, 1])
    problem.addVariable("vreme_sostanok", range(12,21))
    # ----------------------------------------------------

    # ---Tuka dodadete gi ogranichuvanjata----------------
    problem.addConstraint(vremeVoRed, ['vreme_sostanok'])
    problem.addConstraint(simonaPrisutna, ['Simona_prisustvo'])
    problem.addConstraint(odgovaraZaSite, ['Simona_prisustvo', 'Marija_prisustvo', 'Petar_prisustvo', 'vreme_sostanok'])
    problem.addConstraint(simonaIUsteEden, ['Simona_prisustvo', 'Marija_prisustvo', 'Petar_prisustvo'])
    # ----------------------------------------------------

    [print(solution) for solution in problem.getSolutions()]
