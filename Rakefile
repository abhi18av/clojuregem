#SOURCE_FILES?=$$(go list ./... | grep -v /vendor/)
require 'find'
   ALL_SOURCE_FILES = []
 Find.find('./src') do |path|
   ALL_SOURCE_FILES << path if path =~ /.*\.clj.*$/
 end
#puts SOURCE_FILES




##########



desc "Clean CLJS source in CLJ src/clj/clojuregem/sources-cljs/"
task :cljs_in_clj do
  sh ""
end




desc "Clean CLJS source in CLJC src/cljc/clojuregem/sources-cljs/"
task :cljs_in_cljc do
  sh ""
end



desc "Clean CLJS source in CLJS src/cljs/clojuregem/sources-cljs/"
task :cljs_in_cljs do
  sh ""
end



#.DEFAULT_GOAL := build
task :default => :version
