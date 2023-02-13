---------------------------------------------
What:
---------------------------------------------
For the final assignment of the COMP90041 Unit, I designed and implemented a virtual HR assistant software for a fictional company named "Schmoogle" that...
- Allows applicants to submit their applications
- Allow the HR team to post jobs and view their applicants
- Algorithmically analyse applications and matched them their available open positions


This project was made to demonstrate BASIC skills in the Java language and showcase a fundamental understanding in the principles of OOP (Abstraction, Inheritance, Encapsulation, & Polymorphism).

---Design choices were made in accordance to criteria rather than practicality---

---------------------------------------------
Why is this useful:
---------------------------------------------
Each year companies recieve tens of thousands of job applications from graduating students - this puts stress on the HR team to assess individually. The software helps select the appliictions worth taking second look for the HR team for the next stage of the application.

---------------------------------------------
How to get started:
---------------------------------------------

- To use the HR portal
```console
$ java HRAssistant -r hr
```

- To use the HR portal
```console
$ java HRAssistant -r applicant
```

- To call for help (Optional)
```console
$ java HRAssistant -h
```

- To assign path to jobs database (Optional)
```console
$ java HRAssistant -j jobs.csv
```

- To assign path to applications database (Optional)
```console
$ java HRAssistant -j applications.csv
```

- These flags can be used in any permutations (without repetation)
Example 1:
```console
$ java HRAssistant -a applications.csv -j jobs.csv -r hr
```
Example 2:
```console
$ java HRAssistant -j jobs.csv -r applicant -a applications.csv
```
Example 3:
```console
$ java HRAssistant -r hr -j jobs.csv -a applications.csv
```

- Furthermore, there is a standalone flag that displays all arguements
```console
$ java HRAssistant -h
```


---------------------------------------------
Where to get help:
---------------------------------------------



---------------------------------------------
Maintainers and Contributors:
---------------------------------------------
Author: Warren Kuah

