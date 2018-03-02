var=""
for i in $@
do
var+=$i
done
git add *
git commit -m $var
git push origin petar