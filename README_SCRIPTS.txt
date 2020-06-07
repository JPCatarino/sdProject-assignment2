Os ficheiros que terminam em .BAT são para executar em Windows.
Os ficheiros que terminam em .sh são para executar em Linux.

Batch Scripts:

compileSD.BAT - Compila o projecto.
executeSD.BAT - Executa o projecto, abrindo vários terminais, separando os vários clientes e servidores por estes.
buildAndExecute.BAT - Executa os dois scripts anteriores.

Shell Scripts:
compile.sh - Compila o projecto.
executeParallel.sh - Executa o projecto, criando um processo para cada cliente e servidor, sendo que correm em paralelo no mesmo terminal.
executeTerms.sh - Executa o projecto, abrindo vários terminais, separando os vários clientes e servidores por estes.
build_deploy.sh - Compila o projecto e executa um dos scripts anteriores dependendo dos parametros:
		-p Executa em paralelo.
		-m Executa por terminal.

executeVariousTimes.sh - Executa o projecto várias vezes, no modo paralelo. Útil para testar.



