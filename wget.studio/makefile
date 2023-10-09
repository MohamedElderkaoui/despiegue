SITE="http://earthly.dev/blog/cmake-vs-make-diff/"
FOLDER="./READMEfiles"

wget:
#	make cleanwget
	mkdir -p $(FOLDER)
#	echo wget...
	wget -mpEk -np -r -l 2 -P $(FOLDER) -A html,css,jpeg,jpg,bmp,gif,png,pdf $(SITE)

cleanwget:
	rm -rf $(FOLDER) ./index.html

seelocal:
#	find . | grep index | xargs -l bash -c 'ln $0 index.html'  # lo traigo a '.'. Pero rompo enlaces. :(
	find . | grep index | xargs brave-browser &                # lo veo donde esté.



# mkdir -p ./READMEfiles
# wget -mpEk -np -r -l 2 -P ./READMEfiles -A html,css,jpeg,jpg,bmp,gif,png,pdf https://earthly.dev/blog/cmake-vs-make-diff/
