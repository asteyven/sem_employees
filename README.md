# sem_employees

### Badges do not work on private repository 

Master Build  ![GitHub Workflow Status](https://img.shields.io/github/workflow/status/Kevin-Sim/sem_employees/A%20workflow%20for%20my%20Hello%20World%20App?style=flat-square)

License ![GitHub](https://img.shields.io/github/license/Kevin-Sim/sem_employees)

Release ![Releases](https://img.shields.io/github/release/Kevin-Sim/sem_employees)

Codecov [![codecov](https://codecov.io/gh/Kevin-Sim/sem_employees/branch/master/graph/badge.svg?token=iQIPjfryiX)](https://codecov.io/gh/Kevin-Sim/sem_employees)

Lab05

Use Case + diagram and implement Use Case 4 Implement Salaries by Role 


## ToDo

- As an HR advisor I want to produce a report on the salary of all employees so that I can support financial reporting of the organisation.
- As an HR advisor I want to produce a report on the salary of employees in a department so that I can support financial reporting of the organisation.
- As an department manager I want to produce a report on the salary of employees in my department so that I can support financial reporting for my department.
- As an HR advisor I want to produce a report on the salary of employees of a given role so that I can support financial reporting of the organisation.
- As an HR advisor I want to add a new employee's details so that I can ensure the new employee is paid.
- As an HR advisor I want to view and employee's details so that the employee's promotion request can be supported.
- As an HR advisor I want to update an employee's details so that employee's details are kept up-to-date.
- As an HR advisor I want to delete an employee's details so that the company is compliant with data retention legislation.

connect to a running docker container

`docker exec -it [container ID] bash`

connect to sql daemon (prompted for password on connect)

`mysql --user root --password [database name]`

connect to a docker Image

`docker run -it --entrypoint /bin/bash [Image ID]`

Stop all running docker containers from windows powershell

`docker stop $(docker ps -a -q)`

Remove all containers

`docker rm $(docker ps -a -q)`

Remove submodule from commit
`git rm --cached db/test_db`

Create a container from an image make changes and commit to new image

`docker run -it ubuntu`

make changes

commit to image

`docker commit <container ID> <Image name>`

Then restarting new image maintains changes
