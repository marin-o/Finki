Во една компанија сите вработени користат еден централизиран систем, за кој им е креирана корисничка сметка. Корисничко име(username) на секој вработен се добива откако од матичниот број на вработениот ќе се земат деловите кои го даваат датумот на раѓање и ќе се пресмета вредноста:

    ден * 100 + месец * 1000 + година

Така ако делот од матичниот број кој го дава датумот е 1103998, тогаш се добива 11 * 100 + 3 * 1000 + 1998 = 6098.

Доколку бројот кој ќе се добие по пресметката е петцифрен, тогаш тоа е корисничкото име.

Во спротивно, доколку е четирицифрен, тогаш се додава првата цифра да биде:

    7 - доколку годината на раѓање е до 1960
    8 - доколку годината на раѓање е помеѓу 1961 - 1980
    9 - доколку годината на раѓање е помеѓу 1981 - 1999

Напомена: Се смета дека сите вработени се родени до 1999 година.

Програмата го враќа ваквото корисничко име за внес од тастатура кој е дел од матичниот број на некој вработен, како во примерот.