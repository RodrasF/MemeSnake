package workspace.android.memesnake.repository.domain


fun UserDto.toUser() : User = User(this.login, this.avatar_url,this.email?:"", this.followers)

fun MemesDto.toMemesList() : List<Meme> = this.memes.map{ Meme(it.url, it.postLink, it.subreddit, it.title) }

fun MemeDto.toMeme() : Meme = Meme(this.url, this.postLink, this.subreddit, this.title)