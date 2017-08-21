files = Dir['*.jl']

for f in files

	oldFile = f.split(".")[0]
	newFile = oldFile + ".jlir"

	jl_exec = "/usr/local/bin/julia"
	cmd = jl_exec + " jl_file_to_s_expr.jl " + f + " > " + newFile
	system(cmd)

end
