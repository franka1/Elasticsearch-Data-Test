
#! /bin/sh
set -e
BATCHES=300000
BATCHSIZE=250000

javac JSONGenerator.java

load_batch() {
	java JSONGenerator $(($1 *1000 )) > bigLoad$1.json;
        curl -s -XPOST 'localhost:9200/testindex/feature/_bulk' --data-binary @bigLoad$1.json;
        rm bigLoad$1.json;
}

export -f load_batch

for i in {245434..300000..1};
	do sem -j8 load_batch $i;
done;

sem --wait
