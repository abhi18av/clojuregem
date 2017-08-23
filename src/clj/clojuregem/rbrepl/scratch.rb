


require 'parser'
require "unparser"

text1 = "def add9(x) x + 9 end"

p Parser::Ruby23.parse(text1)






module SExp
  def s(type, *children)
    Parser::AST::Node.new(type, children)
  end
end

include SExp



node1 = s(:def, :add9,
  s(:args,
    s(:arg, :x)),
  s(:send,
    s(:lvar, :x), :+,
    s(:int, 9)))


puts Unparser.unparse(node1) 



text2 =  %Q{  def test_many_clients
             with_puma_server do |port|
               clients = concurrently(100.times) { websocket_client(port) }

               concurrently(clients) do |c|
                 assert_equal({ "type" => "welcome" }, c.read_message)  # pop the first welcome message off the stack
                 c.send_message command: "subscribe", identifier: JSON.generate(channel: "ClientTest::EchoChannel")
                 assert_equal({ "identifier" => '{"channel":"ClientTest::EchoChannel"}', "type" => "confirm_subscription" }, c.read_message)
                 c.send_message command: "message", identifier: JSON.generate(channel: "ClientTest::EchoChannel"), data: JSON.generate(action: "ding", message: "hello")
                 assert_equal({ "identifier" => '{"channel":"ClientTest::EchoChannel"}', "message" => { "dong" => "hello" } }, c.read_message)
               end

               concurrently(clients, &:close)
             end
           end }

p Parser::Ruby23.parse(text2)



node2 =  s(:def, :test_many_clients,
  s(:args),
  s(:block,
    s(:send, nil, :with_puma_server),
    s(:args,
      s(:arg, :port)),
    s(:begin,
      s(:lvasgn, :clients,
        s(:block,
          s(:send, nil, :concurrently,
            s(:send,
              s(:int, 100), :times)),
          s(:args),
          s(:send, nil, :websocket_client,
            s(:lvar, :port)))),
      s(:block,
        s(:send, nil, :concurrently,
          s(:lvar, :clients)),
        s(:args,
          s(:arg, :c)),
        s(:begin,
          s(:send, nil, :assert_equal,
            s(:hash,
              s(:pair,
                s(:str, "type"),
                s(:str, "welcome"))),
            s(:send,
              s(:lvar, :c), :read_message)),
          s(:send,
            s(:lvar, :c), :send_message,
            s(:hash,
              s(:pair,
                s(:sym, :command),
                s(:str, "subscribe")),
              s(:pair,
                s(:sym, :identifier),
                s(:send,
                  s(:const, nil, :JSON), :generate,
                  s(:hash,
                    s(:pair,
                      s(:sym, :channel),
                      s(:str, "ClientTest::EchoChannel"))))))),
          s(:send, nil, :assert_equal,
            s(:hash,
              s(:pair,
                s(:str, "identifier"),
                s(:str, "{\"channel\":\"ClientTest::EchoChannel\"}")),
              s(:pair,
                s(:str, "type"),
                s(:str, "confirm_subscription"))),
            s(:send,
              s(:lvar, :c), :read_message)),
          s(:send,
            s(:lvar, :c), :send_message,
            s(:hash,
              s(:pair,
                s(:sym, :command),
                s(:str, "message")),
              s(:pair,
                s(:sym, :identifier),
                s(:send,
                  s(:const, nil, :JSON), :generate,
                  s(:hash,
                    s(:pair,
                      s(:sym, :channel),
                      s(:str, "ClientTest::EchoChannel"))))),
              s(:pair,
                s(:sym, :data),
                s(:send,
                  s(:const, nil, :JSON), :generate,
                  s(:hash,
                    s(:pair,
                      s(:sym, :action),
                      s(:str, "ding")),
                    s(:pair,
                      s(:sym, :message),
                      s(:str, "hello"))))))),
          s(:send, nil, :assert_equal,
            s(:hash,
              s(:pair,
                s(:str, "identifier"),
                s(:str, "{\"channel\":\"ClientTest::EchoChannel\"}")),
              s(:pair,
                s(:str, "message"),
                s(:hash,
                  s(:pair,
                    s(:str, "dong"),
                    s(:str, "hello"))))),
            s(:send,
              s(:lvar, :c), :read_message)))),
      s(:send, nil, :concurrently,
        s(:lvar, :clients),
        s(:block_pass,
          s(:sym, :close))))))


puts Unparser.unparse(node2) 

