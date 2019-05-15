let OSS = require('ali-oss')

let client = new OSS({
  region: 'oss-cn-shanghai',
  accessKeyId: 'LTAI08b2bFELUqe3',
  accessKeySecret: 'QgEOPmy9XdlPjviPG4OLEW30YseWS0',
  bucket: 'iot-cug'
})

// 获取所有文件
async function listDir (dir) {
  let result = await client.list({
    prefix: dir,
    delimiter: '/'
  })
  console.log(result)
  return result.objects
}

// 上传文件
async function put (option) {
  try {
    // for (let i = 0; i < option.file.length; i++) {
    let name = 'video/' + option.file.name
    let result = await client.put(name, option.file)
    console.log(result)
    // }
  } catch (e) {
    console.log(e)
  }
}

// 删除文件
async function deleteV (name) {
  try {
    let result = await client.delete(name)
    console.log(result)
    return result
  } catch (e) {
    console.log(e)
  }
}

async function get (name) {
  let url = client.signatureUrl(name)
  return url
}
export { listDir, put, deleteV, get }
