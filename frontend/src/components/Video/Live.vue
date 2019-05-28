<template>
  <div>
    {{live}}
    <video id="my-video" class="video-js vjs-default-skin" controls preload="auto" poster="@/assets/logo.png">
      <source src="http://47.100.9.221:8090/hls/test.m3u8" type="application/x-mpegURL">
    </video>
  </div>
</template>

<script>
import videojs from 'video.js'
import 'videojs-contrib-hls'

export default {
  name: 'Live',
  data () {
    return {
      live: 'live'
    }
  },
  created () {
  },
  mounted () {
    videojs('my-video', {
      bigPlayButton: false,
      textTrackDisplay: false,
      posterImage: true,
      errorDisplay: false,
      controlBar: true
    }, function () {
      this.play()
    })
  },
  methods: {
  }
}
</script>
