# Arquivo de respostas ao questionário proposto

Respostas ao questionário do Spark.

**Objetivo do cache em Spark**

Quebrar os dados que estão sendo processados e gravá-los em memória. Em alguns casos pode ser utilizado para otimização de performance.

**O Por quê Spark é mais rápido que MapReduce normalmente**

O Spark não utiliza necessariamente a escrita em disco rígido e possue os RDDs para processamento distribuído dos dados em tempo real e os mesmos são armazenados em memória.

**Função do SparkContext**

O SparkContext é utilizado para serem enviadas as tarefas a serem usadas pelos executores do Spark assim como os recursos e configurações a serem utilizadas, ele coordena os processos do cluster de execução.

**Definição RDD**

Podemos imaginar os RDDs do Spark como tabelas que guardam qualquer tipo de dados para serem efetuadas as transformações necessárias. Os RDDs são imutáveis e sempre que efetuada uma alteração é gerado um novo RDD.

Uma grande vantagem do RDD é ele ser fácil de utilizar e seu processamento ser nativamente distribuído.

**O Por quê GroupByKey menos eficiente que ReduceByKey**

O GroupBykey coleta o dado em todos os workers, já o ReduceByKey busca uma chave por partição, apenas uma saída para cada chave é gerada em cada partição.

**Explicação código Scala**

O código Scala enviado é responsável por fazer a contagem das palavras nos arquivos disponíveis no caminho passado como parâmetro do HDFS, após isso o resultado da contagem de cada palavra é gravado em um arquivo novamente no HDFS.

