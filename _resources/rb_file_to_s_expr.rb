function convertFile(input_file_name::String)


f = open(input_file_name,"r")
content = readstring(f)
close(f)

quoted_content = "quote " * content * " end"
expr = parse(quoted_content)

return Meta.show_sexpr(expr)
end

input_file_name = ARGS[1]
convertFile(input_file_name)

