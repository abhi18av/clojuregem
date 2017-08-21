require "parser"
require "unparser"

def convertFile(input_file_name)

content = IO.read(input_file_name)

#expr = Parser::CurrentRuby.parse(content)

expr = Parser::Ruby23.parse(content)
return expr

end




input_file_name = ARGV[0]
p convertFile(input_file_name)


