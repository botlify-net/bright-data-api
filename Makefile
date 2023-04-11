##
## OTOPILOT, 2022
## Botlify Makefile
## File description:
## Generic Makefile for Botlify
##

.PHONY: all test clean fclean install finstall re doc

#=================================
#	Commands
#=================================

all:    finstall

re:     clean all

test:
		mvn test

clean:
		mvn clean

fclean:
		rm -rf target

install:
		mvn install

finstall:
		mvn install -Dmaven.test.skip=true

doc:
		mvn javadoc:javadoc
