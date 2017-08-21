require "parser"
require "unparser"

def convertFile(input_file_name)

f = open(input_file_name,"r")
content = f.readlines()
f.close()

#expr = Parser::CurrentRuby.parse(content)

expr = Parser::Ruby23.parse(content)
return expr

end




input_file_name = ARGV[0]
convertFile(input_file_name)


