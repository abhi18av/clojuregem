require "parser"
require "unparser"

def create_s_expr(expr)

s_expr = Parser::Ruby23.parse(expr)
return s_expr

end



expr = ARGV[0]

#expr = "def add9(x) x + 9 end"
p create_s_expr(expr)


