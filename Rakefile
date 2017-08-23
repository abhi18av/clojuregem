require 'find'
   ALL_SOURCE_FILES = []
   Find.find('./src') do |path|
   ALL_SOURCE_FILES << path if path =~ /.*\.clj.*$/
 end

#puts ALL_SOURCE_FILES

 CLJS_SOURCE_IN_CLJ = []

 CLJS_SOURCE_IN_CLJC = []

 CLJS_SOURCE_IN_CLJS = []


arr = ALL_SOURCE_FILES

for i in (0..arr.length-1)

  #puts arr[i].split("/")[2]
  folder_name = arr[i].split("/")[2]
  file_name =  arr[i]

  case

    when folder_name == "clj"
      #puts "CLJ"
      CLJS_SOURCE_IN_CLJ << file_name

   when folder_name == "cljc"
    #puts "CLJC"
    CLJS_SOURCE_IN_CLJC << file_name

   when folder_name == "cljs"
    #puts "CLJS"
    CLJS_SOURCE_IN_CLJS << file_name

  else
    puts "Check the logic again"

  end

end


#puts CLJS_SOURCE_IN_CLJ

#puts CLJS_SOURCE_IN_CLJS

#puts CLJS_SOURCE_IN_CLJC

##########


def clean_up(array, extension)
for f in array

  if f.include?("sources-cljs")

    file_extension = f.split(".")[-1]

    if file_extension == extension
      #puts f
      nil
    else
      cmd = "rm " + f
      #puts "==> " + cmd
      system(cmd)
    end
  end

end

end


##########
desc "Clean CLJS source in CLJ src/clj/clojuregem/sources-cljs/"
task :clean_cljs_in_clj do
  clean_up(CLJS_SOURCE_IN_CLJ, "clj")
end




desc "Clean CLJS source in CLJC src/cljc/clojuregem/sources-cljs/"
task :clean_cljs_in_cljc do
  clean_up(CLJS_SOURCE_IN_CLJC, "cljc")
end



desc "Clean CLJS source in CLJS src/cljs/clojuregem/sources-cljs/"
task :clean_cljs_in_cljs do
  clean_up(CLJS_SOURCE_IN_CLJS, "cljs")
end


########

task :default => :description

desc "Automates the grooming of CLJS source code in Chestnut project"
task :description do
  puts "This rakefile automates the deletion of the excessive files from the CLJS source as per chestnut's project structure"
end
