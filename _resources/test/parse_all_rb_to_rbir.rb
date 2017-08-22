files = Dir['*.rb']

for f in files

	oldFile = f.split(".")[0]
	newFile = oldFile + ".rbir"

	rb_exec = "ruby"
	cmd = rb_exec + " file_to_rbir.rb " + f + " > " + newFile
	system(cmd)

end
