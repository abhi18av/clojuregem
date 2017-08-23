#SOURCE_FILES?=$$(go list ./... | grep -v /vendor/)
require 'find'
   SOURCE_FILES = []
 Find.find('./cmd') do |path|
   SOURCE_FILES << path if path =~ /.*\.go$/
 end
#puts SOURCE_FILES


#TEST_PATTERN?=.
TEST_PATTERN ="."

#TEST_OPTIONS?=-race
TEST_OPTIONS="-race"



##########



desc "Golang version"
task :version do
  sh "go version"
end





##########
# DONE
#setup: ## Install all the build and lint dependencies
#	go get -u github.com/alecthomas/gometalinter
#	go get -u github.com/golang/dep/...
#	go get -u github.com/pierrre/gotestcover
#	go get -u golang.org/x/tools/cmd/cover
#	dep ensure
#	gometalinter --install --update

 
desc "Setup golang tool suite"
task :setup do
	sh "go get -u github.com/alecthomas/gometalinter"
	sh "go get -u github.com/golang/dep/..."
	sh "go get -u github.com/pierrre/gotestcover"
	sh "go get -u golang.org/x/tools/cmd/cover"
	sh "dep ensure"
	sh "gometalinter --install --update"
end


##########




#test: ## Run all the tests
#	gotestcover $(TEST_OPTIONS) -covermode=atomic -coverprofile=coverage.txt $(SOURCE_FILES) -run $(TEST_PATTERN) -timeout=30s


desc "Run all the tests"
task :test do
for f in SOURCE_FILES
	sh "gotestcover " + TEST_OPTIONS + " -covermode=atomic -coverprofile=coverage.txt " + f + " -run " + TEST_PATTERN + " -timeout=30s"
end
end

##########


#cover: test ## Run all the tests and opens the coverage report
#	go tool cover -html=coverage.txt

desc "Run all the tests and opens the coverage report"
task :cover do
	Rake::Task["test"].invoke
	sh "go tool cover -html=coverage.txt"
end


##########

#fmt: ## gofmt and goimports all go files
#	find . -name '*.go' -not -wholename './vendor/*' | while read -r file; do gofmt -w -s "$$file"; goimports -w "$$file"; done



desc "gomt and goimports all go files"
task :fmt do

for f in SOURCE_FILES
 
sh "gofmt -w -s " + f
sh "goimports -w " + f


end

end

##########


#lint: ## Run all the linters
#	gometalinter --vendor --disable-all \
#		--enable=deadcode \
#		--enable=ineffassign \
#		--enable=gosimple \
#		--enable=staticcheck \
#		--enable=gofmt \
#		--enable=goimports \
#		--enable=dupl \
#		--enable=misspell \
#		--enable=errcheck \
#		--enable=vet \
#		--enable=vetshadow \
#		--deadline=10m \
#		./...



desc "Run all the linters"
task :lint do

sh "gometalinter --vendor --disable-all \
		--enable=deadcode \
		--enable=ineffassign \
		--enable=gosimple \
		--enable=staticcheck \
		--enable=gofmt \
		--enable=goimports \
		--enable=dupl \
		--enable=misspell \
		--enable=errcheck \
		--enable=vet \
		--enable=vetshadow \
		--deadline=10m \
		./..."
end

##########


#ci: lint test ## Run all the tests and code checks


desc "Run all the tests and code checks"
task :ci do
	Rake::Task["lint"].invoke
	Rake::Task["test"].invoke
end

##########


#build: ## Build a beta version
#	go build -o ./bin/saigo ./cmd/saigo/main.go


desc "Build a beta version"
task :build do
	sh "go build -o ./bin/saigo ./main.go"
end

##########


#install: ## Install to $GOPATH/src
#	go install ./cmd/...


desc "Install to $GOPATH/src"
task :install do
sh "go install ./..."
end

##########


#.DEFAULT_GOAL := build
task :default => :version
