Да се напише функција revertString(char *a, char *b, char *comparator) која ќе ги преврти стринговите a и b и потоа ќе врати еден од нив во зависност од стрингот comparator, кој доколку има вредност pogolem се враќа лексикографски поголемиот или доколку има вредност pomal се враќа лексикографски помалиот. Доколку двата стрингови се еднакви, не е битно кој стринг ќе се врати.

Во главниот дел од програмата треба N пати да се прочитаат 3 стрингови и за нив да се испечати резулататот од revertString функцијата.

Забелешка: За стрингот comparator се смета дека не е case-sensitive. На пример за вредностите poGolEm и POGoleM функцијата треба да врати ист резултат. Доколку пак comparator има непозната вредност да се врати стрингот "Invalid comparator".