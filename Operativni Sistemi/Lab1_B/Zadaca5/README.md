Со помош на синхронизациските методи да се реши проблемот за определување на бројот на појавувања на карактерите А и B во огромна низа и нивно запишување во две глобални променливи countА и countB. Н

Секвенцијалното решение не е прифатливо поради тоа што трае многу долго време (поради големината на низата). За таа цел, потребно е да се паралелизира овој процес, при што треба да се напише метода која ќе ги брои појавувањата на карактерите А и В во помал фрагмент од низата. На крај просечната вредност на двете променливи countA и countB треба да се запише во глобалната променлива average. Пресметката на вредностите на countA и countB треба да се прави асинхроно во посебни нитки за секоја променлива посебно.

Напомена: Почетниот код е даден во CountАB.java. Задачата да се тестира над низа од минимум 1000 елементи.
----

Solve the issue of detecting the number of occurrences of the characters A and B in a large array by using thread synchronization methods. The counts are written / incremented in two global variables countA and countB on each find.

The standard sequential solution is not acceptable as it takes a long time (because the array is very large). Therefore, you need to implement this process and write a method which will count the occurrences of A and B in smaller fragments of the array. At the end, the average value of countA and countB should be written in the globalVariable average. The calculation of the values for countA and countB should be done asynchronously in separate threads for each variable individually.

Note: The starting code for the solutions is given in CountAB.java. You need to test it with an array of at least 1.000 elements.
