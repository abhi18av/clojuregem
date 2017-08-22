files = Dir['*.rb']

for f in files

	oldFile = f.split(".")[0]
	newFile = oldFile + ".rbir"

	rb_exec = "ruby"
	cmd = rb_exec + " rb_file_to_s_expr.rb " + f + " > " + newFile
	system(cmd)

end
