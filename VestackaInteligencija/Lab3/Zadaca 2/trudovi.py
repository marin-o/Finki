from constraint import *

def all_assigned(*args):
    return all(itm in ['T1', 'T2', 'T3', 'T4'] for itm in args)

def constraint(*lista):
    termini = dict()
    for i in range(4):
        termini[f'T{i+1}'] = 0
    for arg in lista:
        termini[arg] += 1
    return all(v <= 4 for v in termini.values())


if __name__ == '__main__':
    num = int(input())

    papers = dict()

    paper_info = input()
    while paper_info != 'end':
        title, topic = paper_info.split(' ')
        papers[title] = topic
        paper_info = input()

    # Tuka definirajte gi promenlivite
    # variables = [str(x + " (" + y + ")") for (x, y) in papers.items()]
    # variables = papers.keys()
    domain = [f'T{i + 1}' for i in range(num)]

    problem = Problem(BacktrackingSolver())

    # Dokolku vi e potrebno moze da go promenite delot za dodavanje na promenlivite

    AI_list = [f'{key} ({value})' for key, value in papers.items() if value == 'AI']
    ML_list = [f'{key} ({value})' for key, value in papers.items() if value == 'ML']
    NLP_list = [f'{key} ({value})' for key, value in papers.items() if value == 'NLP']

    problem.addVariables(AI_list, domain)
    problem.addVariables(ML_list, domain)
    problem.addVariables(NLP_list, domain)

    # Tuka dodadete gi ogranichuvanjata
    if 0 < len(AI_list) <= 4:
        problem.addConstraint(AllEqualConstraint(), AI_list)
    if 0 < len(ML_list) <= 4:
        problem.addConstraint(AllEqualConstraint(), ML_list)
    if 0 < len(NLP_list) <= 4:
        problem.addConstraint(AllEqualConstraint(), NLP_list)

    problem.addConstraint(constraint, AI_list + ML_list + NLP_list)
    result = problem.getSolution()

    # Tuka dodadete go kodot za pechatenje
    p = [f'{key}: {value}' for key, value in result.items()]
    p.sort()
    for item in p:
        print(item)
