var1 = ""
for i in $@
do
var1 += i
done
git add *
git commit -m var1
git push origin petar