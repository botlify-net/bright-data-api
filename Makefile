##
## OTOPILOT, 2022
## Botlify Makefile
## File description:
## Generic Makefile for Botlify
##

.PHONY: all test clean fclean re doc

#=================================
#	Commands
#=================================

all:    finstall

re:     clean all

test:
		mvn test

clean:
		mvn clean

finstall:
		mvn install -Dmaven.test.skip=true

doc:
		mvn javadoc:javadoc
