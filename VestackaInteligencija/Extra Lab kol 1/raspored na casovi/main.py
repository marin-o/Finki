from constraint import *

# def preklopuvanje(*args):
#     monday = list()
#     tuesday = list()
#     wednesday = list()
#     thursday = list()
#     friday = list()
#     for word in args:
#         if word.startswith('Mon'):
#             monday.append(word)
#             continue
#         if word.startswith('Tue'):
#             tuesday.append(word)
#             continue
#         if word.startswith('Wed'):
#             wednesday.append(word)
#             continue
#         if word.startswith('Thu'):
#             thursday.append(word)
#             continue
#         if word.startswith('Fri'):
#             friday.append(word)
#
#     for counter in range(len(monday)):
#         for j in range(counter+1, len(monday)):
#             timeStart = int(monday[counter][-2:])
#             otherTime = int(monday[j][-2:])
#             if otherTime - timeStart < 2:
#                 return False
#
#     for counter in range(len(tuesday)):
#         for j in range(counter+1, len(tuesday)):
#             timeStart = int(tuesday[counter][-2:])
#             otherTime = int(tuesday[j][-2:])
#             if otherTime - timeStart < 2:
#                 return False
#
#     for counter in range(len(wednesday)):
#         for j in range(counter+1, len(wednesday)):
#             timeStart = int(wednesday[counter][-2:])
#             otherTime = int(wednesday[j][-2:])
#             if otherTime - timeStart < 2:
#                 return False
#
#     for counter in range(len(thursday)):
#         for j in range(counter+1, len(thursday)):
#             timeStart = int(thursday[counter][-2:])
#             otherTime = int(thursday[j][-2:])
#             if otherTime - timeStart < 2:
#                 return False
#
#     for counter in range(len(friday)):
#         for j in range(counter+1, len(friday)):
#             timeStart = int(friday[counter][-2:])
#             otherTime = int(friday[j][-2:])
#             if otherTime - timeStart < 2:
#                 return False
#
#     return True


def preklopuvanje(*args):
    days = {'Mon': [], 'Tue': [], 'Wed': [], 'Thu': [], 'Fri': []}

    for word in args:
        day = word[:3]
        if day in days:
            days[day].append(word)

    for day in days.values():
        for counter in range(len(day)):
            for j in range(counter+1, len(day)):
                timeStart = int(day[counter][-2:])
                otherTime = int(day[j][-2:])
                if otherTime - timeStart < 2:
                    return False

    return True



if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    casovi_AI = input()
    casovi_ML = input()
    casovi_R = input()
    casovi_BI = input()

    AI_predavanja_domain = ["Mon_11", "Mon_12", "Wed_11", "Wed_12", "Fri_11", "Fri_12"]
    ML_predavanja_domain = ["Mon_12", "Mon_13", "Mon_15", "Wed_12", "Wed_13", "Wed_15", "Fri_11", "Fri_12", "Fri_15"]
    R_predavanja_domain = ["Mon_10", "Mon_11", "Mon_12", "Mon_13", "Mon_14", "Mon_15", "Wed_10", "Wed_11", "Wed_12",
                           "Wed_13", "Wed_14", "Wed_15", "Fri_10", "Fri_11", "Fri_12", "Fri_13", "Fri_14", "Fri_15"]
    BI_predavanja_domain = ["Mon_10", "Mon_11", "Wed_10", "Wed_11", "Fri_10", "Fri_11"]

    AI_vezbi_domain = ["Tue_10", "Tue_11", "Tue_12", "Tue_13", "Thu_10", "Thu_11", "Thu_12", "Thu_13"]
    ML_vezbi_domain = ["Tue_11", "Tue_13", "Tue_14", "Thu_11", "Thu_13", "Thu_14"]
    BI_vezbi_domain = ["Tue_10", "Tue_11", "Thu_10", "Thu_11"]

    # ---Tuka dodadete gi promenlivite--------------------
    casoviAI = list()
    casoviML = list()
    casoviR = list()
    casoviBI = list()
    for i in range(int(casovi_AI)):
        casoviAI.append(f'AI_cas_{i+1}')

    for i in range(int(casovi_ML)):
        casoviML.append(f'ML_cas_{i+1}')

    for i in range(int(casovi_R)):
        casoviR.append(f'R_cas_{i+1}')

    for i in range(int(casovi_BI)):
        casoviBI.append(f'BI_cas_{i+1}')

    problem.addVariables(casoviAI, AI_predavanja_domain)
    problem.addVariables(casoviML, ML_predavanja_domain)
    problem.addVariables(casoviR, R_predavanja_domain)
    problem.addVariables(casoviBI, BI_predavanja_domain)
    problem.addVariables(['AI_vezbi'], AI_vezbi_domain)
    problem.addVariables(['ML_vezbi'], ML_vezbi_domain)
    problem.addVariables(['BI_vezbi'], BI_vezbi_domain)

    allVariables = list()
    for string in casoviAI:
        allVariables.append(string)

    for string in casoviML:
        allVariables.append(string)

    for string in casoviR:
        allVariables.append(string)

    for string in casoviBI:
        allVariables.append(string)

    allVariables.append('AI_vezbi')
    allVariables.append('ML_vezbi')
    allVariables.append('BI_vezbi')
    # ---Tuka dodadete gi ogranichuvanjata----------------
    problem.addConstraint(AllDifferentConstraint(), casoviAI)
    problem.addConstraint(AllDifferentConstraint(), casoviML)
    problem.addConstraint(AllDifferentConstraint(), casoviR)
    problem.addConstraint(AllDifferentConstraint(), casoviBI)
    problem.addConstraint(AllDifferentConstraint(), allVariables)
    problem.addConstraint(preklopuvanje, allVariables)
    # ----------------------------------------------------
    solution = problem.getSolution()

    print(solution)