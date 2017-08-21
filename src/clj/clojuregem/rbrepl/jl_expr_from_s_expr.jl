

# parse_reverse: parse expr in reverse :p 
function parse_reverse(S_expr::Tuple)

    expr =  Any[isa(i, Tuple) ? parse_reverse(i) : i for i in S_expr]
    #println(expr)
    return Expr(expr... );

end
 


expr = ARGS[1]
parsed_expr = parse(expr) |> eval

parse_reverse(parsed_expr) |> show