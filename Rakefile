
require 'find'
   ALL_SOURCE_FILES = []
 Find.find('./src') do |path|
   ALL_SOURCE_FILES << path if path =~ /.*\.clj.*$/
 end
puts ALL_SOURCE_FILES


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

