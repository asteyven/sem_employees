# sem_employees

### Badges do not work on private repository.

Master Build  ![GitHub Workflow Status](https://img.shields.io/github/workflow/status/Kevin-Sim/sem_employees/A%20workflow%20for%20my%20Hello%20World%20App?style=flat-square)

License ![GitHub](https://img.shields.io/github/license/Kevin-Sim/sem_employees)

![Releases](https://img.shields.io/github/release/Kevin-Sim/sem_employees)

Lab03

Mac MySQL Image Fix added 2 lines in db Dockerfile

```yml
# Use the latest MySQL image
# FROM mysql
# Mac Fix
FROM mysql/mysql-server
# Set the working directory
WORKDIR /tmp
# Copy all the files to the working directory of the container
COPY test_db/*.sql /tmp/
COPY test_db/*.dump /tmp/
# Copy the main SQL file to docker-entrypoint-initdb.d.
# Scripts and SQL files in this folder are executed on container startup.
# This is specific to MySQL.
COPY test_db/employees.sql /docker-entrypoint-initdb.d
# Set the root password
ENV MYSQL_ROOT_PASSWORD example
#Mac Fix
ENV MYSQL_ROOT_HOST=%
```

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
