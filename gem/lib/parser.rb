require 'nokogiri'

puts "nokogiri #{Nokogiri::VERSION_INFO.inspect}"

class Parser

  def parse(uri)
    xml = Nokogiri::XML.parse(File.read(uri.sub(/jar:/,'')))
    xml.xpath('//name').collect { |x| x.children.first.to_s }
  end
end
