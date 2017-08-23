require 'find'
   ALL_SOURCE_FILES = []
 Find.find('./src') do |path|
   ALL_SOURCE_FILES << path if path =~ /.*\.clj.*$/
 end

#puts ALL_SOURCE_FILES

 CLJS_IN_CLJ = []

 CLJS_IN_CLJC = []

 CLJS_IN_CLJS = []


arr = ALL_SOURCE_FILES

for i in (0..arr.length-1)

  #puts arr[i].split("/")[2]
  folder_name = arr[i].split("/")[2]

  case

    when folder_name == "clj"
    puts "CLJ"

   when folder_name == "cljc"
    puts "CLJC"

   when folder_name == "cljs"
    puts "CLJS"

  else
    puts "Check th
for i in (0..arr.length)

  #puts arr[i].split("/")[2]
  folder_name = arr[i].split("/")[2] 

  case

    when folder_name == "clj"
    puts "CLJ"

  when folder_name == "cljc"
    puts "CLJC"

  when folder_name == "cljs"
    puts "CLJS"

  else
    puts "Check the logic again"
end

end

e logic again"

  end

end





##########



desc "Clean CLJS source in CLJ src/clj/clojuregem/sources-cljs/"
task :clean_cljs_in_clj do
  sh ""
end




desc "Clean CLJS source in CLJC src/cljc/clojuregem/sources-cljs/"
task :clean_cljs_in_cljc do
  sh ""
end



desc "Clean CLJS source in CLJS src/cljs/clojuregem/sources-cljs/"
task :clean_cljs_in_cljs do
  sh ""
end


########

task :default => :description

desc "Automates the grooming of CLJS source code in Chestnut project"
task :description do
  puts "This rakefile automates the deletion of the excessive files from the CLJS source as per chestnut's project structure"
end
