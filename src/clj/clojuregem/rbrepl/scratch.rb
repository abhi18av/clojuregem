require 'parser/current'

p Parser::CurrentRuby.parse("2 + 2")


require "unparser"




module SExp
  def s(type, *children)
    Parser::AST::Node.new(type, children)
  end
end

include SExp



node = s(:def,
  :foo,
  s(:args,
    s(:arg, :x)
   ),
  s(:send,
    s(:lvar, :x),
    :+,
    s(:int, 3)
   )
)

puts Unparser.unparse(node) # => "def foo(x)\n  x + 3\nend"


