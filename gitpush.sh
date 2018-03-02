var=""
for i in $@
do
var+=$i
var+= 
done
git add *
git commit -m $var
git push origin petar