#! /bin/sh
set -e
BATCHES=300000
BATCHSIZE=250000


for i in {166306..300000..1};
	do javac JSONGenerator.java && java JSONGenerator $(($i *1000 )) > bigLoad$i.json;
	   curl -s -XPOST 'localhost:9200/testindex/feature/_bulk' --data-binary @bigLoad$i.json;
	   rm bigLoad*;
done;
