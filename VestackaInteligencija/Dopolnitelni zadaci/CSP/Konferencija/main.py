import collections

from constraint import *


def maxFour(*args):
    for term in domain:
        count = args.count(term)
        if count > 4:
            return False
    return True


def upToFour(*args):
    if len(args) > 4 or len(args) == 0:
        return True
    if len(args) <= 4:
        for term in domain:
            count = args.count(term)
            if count == len(args):
                return True
    return False


if __name__ == '__main__':
    num = int(input())

    papers = dict()

    paper_info = input()
    while paper_info != 'end':
        title, topic = paper_info.split(' ')
        papers[title] = topic
        paper_info = input()

    # Tuka definirajte gi promenlivite

    domain = [f'T{i + 1}' for i in range(num)]

    problem = Problem(BacktrackingSolver())

    variables = list()

    ai = [f'{key} ({value})' for key, value in papers.items() if value == 'AI']
    ml = [f'{key} ({value})' for key, value in papers.items() if value == 'ML']
    nlp = [f'{key} ({value})' for key, value in papers.items() if value == 'NLP']

    # Dokolku vi e potrebno moze da go promenite delot za dodavanje na promenlivite
    problem.addVariables(ai, domain)
    problem.addVariables(ml, domain)
    problem.addVariables(nlp, domain)

    # Tuka dodadete gi ogranichuvanjata

    problem.addConstraint(maxFour, variables)
    if 0 < len(ai) <= 4:
        problem.addConstraint(upToFour, ai)
    if 0 < len(ml) <= 4:
        problem.addConstraint(upToFour, ml)
    if 0 < len(nlp) <= 4:
        problem.addConstraint(upToFour, nlp)

    result = problem.getSolution()

    # Tuka dodadete go kodot za pechatenje
    orderedResult = sorted(result.items(), key=lambda x: int(x[0].split('Paper')[1].split(' ')[0]))
    for tup in orderedResult:
        print(f'{tup[0]}: {tup[1]}')
