require "parser"
require "unparser"


module SExp
  def s(type, *children)
    Parser::AST::Node.new(type, children)
  end
end

include SExp



node = ARGV[0]


evald_node = eval(node)

puts Unparser.unparse(evald_node)


