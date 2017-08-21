require "parser"
require "unparser"

def convertFile(input_file_name)

f = open(input_file_name,"r")
content = readstring(f)
close(f)

expr = Parse::CurrentRuby.parse(content)

return expr

end




input_file_name = ARGS[1]
convertFile(input_file_name)

