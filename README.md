---------------------------------------------
What:
---------------------------------------------
For the final assignment of the COMP90041 Unit, I designed and implemented a virtual HR assistant software for a fictional company named "Schmoogle" that...
- Allows applicants to submit their applications
- Allow the HR team to post jobs and view their applicants
- Algorithmically analyse applications and matched them their available open positions


This project was made to demonstrate BASIC skills in the Java language and showcase a fundamental understanding in the principles of OOP (Abstraction, Inheritance, Encapsulation, Polymorphism...)

---------------------------------------------
Why is this useful:
---------------------------------------------
Each year companies recieve tens of thousands of job applications from graduating students - this puts stress on the HR team to assess individually. The software helps select the appliictions worth taking second look for the HR team for the next stage of the application.

---------------------------------------------
How to get started:
---------------------------------------------
1. Create and start virtual environment:

```console
$ python -m venv venv
$ source venv/bin/activate
```

2. Install the modules from 'requirements.txt':
```console
$ python -m pip install -r requirements.txt
```

2a. (Optional) Initiate/restart database:
```console
$ python build_database.py
```

3. Start Flask App:
```console
$ python app.py
```

4. Explore:
Homepage link: `http://127.0.0.1:8000`
Swagger UI API documentation link: `http://127.0.0.1:8000/api/ui`
User list link: `http://127.0.0.1:8000/api/people`
Notes list link: `http://127.0.0.1:8000/api/notes`


---------------------------------------------
Where to get help:
---------------------------------------------



---------------------------------------------
Maintainers and Contributors:
---------------------------------------------
Author: Warren Kuah

