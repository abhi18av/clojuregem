require "parser"
require "unparser"

def create_s_expr(expr)

s_expr = Parser::Ruby23.parse(expr)
return s_expr

end




expr = ARGV[0]
p create_s_expr(expr)


