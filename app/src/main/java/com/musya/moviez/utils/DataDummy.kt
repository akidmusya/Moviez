package com.musya.moviez.utils

import com.musya.moviez.data.source.local.entity.EpisodeEntity
import com.musya.moviez.data.source.local.entity.MovieEntity
import com.musya.moviez.data.source.remote.response.*

object DataDummy{
    fun generateDummyMovieResponse(): MoviesResponse{
        val list = ArrayList<MoviesResultsItem>()

        list.add(MoviesResultsItem(
                "2021-04-07",
                7.6,
                1,
                "Mortal Kombat",
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg"
        ))

        list.add(MoviesResultsItem("2021",
                8.1,
                2,
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg"
        ))

        list.add(MoviesResultsItem( "2021",
                8.5,
                3,
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg"
        ))

        list.add(MoviesResultsItem( "2021",
                8.5,
                4,
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg"
        ))

        list.add(MoviesResultsItem( "2021",
                7.5,
                5,
                "The Marksman",
                "/6vcDalR50RWa309vBH1NLmG2rjQ.jpg"
        ))
        list.add(MoviesResultsItem( "2021",
                8.2,
                6,
                "Raya and the Last Dragon",
                "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg"
        ))
        list.add(MoviesResultsItem( "2021",
                8.4,
                7,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg"
        ))
        list.add(MoviesResultsItem( "2021",
                6.9,
                8,
                "Silk Road",
                "/6KxiEWyIDpz1ikmD7nv3GTX4Uoj.jpg"
        ))
        list.add(MoviesResultsItem( "2021",
                7.2,
                9,
                "Space Sweepers",
                "p9YDHJKvUoi7v2SCd3vLGPae1Xp.jpg"
        ))
        list.add(MoviesResultsItem( "2021",
                5.5,
                10,
                "The Unholy",
                "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg"
        ))

        return MoviesResponse(1, list)
    }

    fun generateDummyTvResponse(): TvResponse{
        val list = ArrayList<ResultsItem>()

        list.add(ResultsItem( 7.7,
                "The Flash",
                60735,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
        ))

        list.add(ResultsItem(8.5,
                "Lucifer",
                11,
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"
        ))
        list.add(ResultsItem( 8.9,
                "Vincenzo",
                10,
                "/dvXJgEDQXhL9Ouot2WkBHpQiHGd.jpg"
        ))
        list.add(ResultsItem( 8.2,
                "Grey's Anatomy",
                9,
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg"
        ))
        list.add(ResultsItem( 8.2,
                "The Handmaid's Tale",
                8,
                "/oIkxqt6ug5zT5ZSUUyc1Iqopf02.jpg"
        ))
        list.add(ResultsItem( 7.9,
                "The Falcon and The Winter Soldier",
                2,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg"
        ))
        list.add(ResultsItem( 8.9,
                "Invicible",
                3,
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg"
        ))
        list.add(ResultsItem( 7.5,
                "Selena: The Series",
                4,
                "/mYsWyfiIMxx4HDm0Wck7oJ9ckez.jpg"
        ))
        list.add(ResultsItem( 7.7,
                "The Flash",
                5,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
        ))
        list.add(ResultsItem( 8.6,
                "The Good Doctor",
                6,
                "/53P8oHo9cfOsgb1cLxBi4pFY0ja.jpg"
        ))
        list.add(ResultsItem( 9.0,
                "The Bad Batch",
                7,
                "/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg"
        ))

        return TvResponse(1, list)
    }

    fun generateMovieDetailResponse(): MovieDetailResponse{
        val genres = ArrayList<MovieGenresItem>()

        genres.add(MovieGenresItem("Comedy", 1))
        genres.add(MovieGenresItem("Family", 2))

        return MovieDetailResponse(
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26",
                genres,
                8.6,
                134,
                1,
                "Cruella",
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg")
    }

    fun generateTvDetailResponse(): TvDetailResponse{
        val genres = ArrayList<GenresItem>()

        genres.add(GenresItem("Crime, Sci-Fi & Fantasy", 1))

        return TvDetailResponse(
                "2016-01-25",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                genres,
                8.5,
                "Lucifer",
                11,
                1,
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
        )
    }

    fun generateEpisodeResponse(numberOfEpisode: Int): EpisodesResponse{
        val list = ArrayList<EpisodesItem>()

        for (i in 1..numberOfEpisode){
            list.add(EpisodesItem("Overview episode $i",
                    i,
                    "Episode $i",
                    i
            ))
        }

        return EpisodesResponse(
                "1",
                list
        )
    }

    fun generateDummyMovies(series: Boolean): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        if (!series){
            movies.add(
                MovieEntity(1,
                        "1",
                    "Cruella",
                    8.6,
                    "2021-05-26",
                    "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                    "Emma Stone, Emma Thompson, Joel Fry, Paul Walter Hauser, Mark Strong, Tipper Seifert-Cleveland, Kirby Howell-Baptiste, Emily Beecham",
                    "Comedy, Crime",
                    134,
                    "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                    0,
                true
                )
            )
            movies.add(
                MovieEntity(2,
                        "2",
                    "Godzilla vs. Kong",
                    8.1,
                    "2021",
                    "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                    "Alexander Skarsgård, Millie Bobby Brown, Rebecca Hall, Brian Tyree Henry, Shun Oguri, Eiza González, Julian Dennison, ",
                    "Science Fiction, Action, Drama",
                    0,
                    "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                    0,
                        true
            )
            )
            movies.add(
                MovieEntity( 3,
                        "3",
                    "Nobody",
                    8.5,
                    "2021",
                    "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                    "Bob Odenkirk, Aleksey Serebryakov, Connie Nielsen, Christopher Lloyd",
                    "Action, Thriller, Crime",
                    0,
                    "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                    0
            )
            )
            movies.add(
                MovieEntity( 4,
                        "4",
                    "Zack Snyder's Justice League",
                    8.5,
                    "2021",
                    "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                    "Ben Affleck, Henry Cavill, Gal Gadot, Jason Momoa, Ezra Miller, Ray Fisher",
                    "Action, Adventure, Science Fiction",
                    0,
                    "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                    0
            )
            )
            movies.add(
                MovieEntity( 5,
                        "5",
                    "The Marksman",
                    7.5,
                    "2021",
                    "Jim Hanson’s quiet life is suddenly disturbed by two people crossing the US/Mexico border – a woman and her young son – desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy’s reluctant defender. He embraces his role as Miguel’s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins.",
                    "Liam Neeson, Katheryn Winnick, Teresa Ruiz, Juan Pablo Raba",
                    "Action, Thriller, Crime",
                    0,
                    "/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
                    0
            )
            )
            movies.add(
                MovieEntity( 6,
                        "6",
                    "Raya and the Last Dragon",
                    8.2,
                    "2021",
                    "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                    "Kelly Marie Tran, Awkwafina, Izaac Wang",
                    "Animation, Adventure, Fantasy, Family",
                    0,
                    "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                    0
            )
            )
            movies.add(
                MovieEntity( 7,
                        "7",
                    "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                    8.4,
                    "2021",
                    "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                    "Natsuki Hanae, Akari Kito, Hiro Shimono, Yoshitsugu Matsuoka",
                    "Animation, Action, Adventure, Fantasy, Drama",
                    0,
                    "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                    0
            )
            )
            movies.add(
                MovieEntity( 8,
                        "8",
                    "Silk Road",
                    6.9,
                    "2021",
                    "The true story of Ross Ulbricht, the charismatic young tech-mastermind who unleashed the darknet website Silk Road, and the corrupt DEA agent determined to bring down his billion-dollar empire.",
                    "Jason Clarke, Nick Robinson, Daniel David Stewart, Alexandra Shipp, Paul Walter Hauser, Jimmi Simpson",
                    "Crime, Thriller, Drama",
                    0,
                    "/6KxiEWyIDpz1ikmD7nv3GTX4Uoj.jpg",
                    0
            )
            )
            movies.add(
                MovieEntity( 9,
                        "9",
                    "Space Sweepers",
                    7.2,
                    "2021",
                    "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
                    "Song Joong-ki, Kim Tae-ri, Yoo Hae-jin, Jin Sun-kyu, Richard Armitage, Kim Moo-yul",
                    "Science Fiction, Drama",
                    0,
                    "/p9YDHJKvUoi7v2SCd3vLGPae1Xp.jpg",
                    0
            )
            )
            movies.add(
                MovieEntity( 10,
                        "10",
                    "The Unholy",
                    5.5,
                    "2021",
                    "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                    "Jeffrey Dean Morgan, Cricket Brown, Cary Elwes",
                    "Horror",
                    0,
                    "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                    0
            )
            )
        } else {
            movies.add(
                    MovieEntity( 5,
                            "5",
                            "Loki",
                            8.1,
                            "2021-06-09",
                            "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                            "Tom Hiddleston, Owen Wilson, Sophia Di Martino, Gugu Mbatha-Raw, Wunmi Mosaku, Eugene Cordero, Tara Strong",
                            "Drama, Sci-Fi & Fantasy",
                            0,
                            "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                            6,
                            true
                    )
            )
            movies.add(
                    MovieEntity( null,
                            "11",
                            "Lucifer",
                            8.5,
                            "2016-01-25",
                            "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                            "Tom Ellis, Lauren German, Kevin Alejandro, D.B. Woodside, Lesley-Ann Brandt, Aimee Garcia, Rachael Harris",
                            "Crime, Sci-Fi & Fantasy",
                            0,
                            "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                            13
                    )
            )
            movies.add(
                MovieEntity( 60735,
                        "60735",
                    "The Flash",
                    7.7,
                    "2014-10-07",
                    "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                    "Grant Gustin, Candice Patton, Danielle Panabaker, Jesse L. Martin, Kayla Compton, Brandon McKnight, Danielle Nicolet",
                    "Drama, Sci-Fi & Fantasy",
                    0,
                    "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                    13
            )
            )
            movies.add(
                MovieEntity( 2,
                        "2",
                    "The Falcon and the Winter Soldier",
                    7.9,
                    "2021-03-19",
                    "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                    "Anthony Mackie, Sebastian Stan, Emily VanCamp, Wyatt Russell, Daniel Brühl",
                    "Sci-Fi & Fantasy, Action & Adventure, Drama, War & Politics",
                    0,
                    "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                    6
            )
            )
            movies.add(
                MovieEntity( 3,
                        "3",
                    "Invincible",
                    8.9,
                    "2021",
                    "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                    "Steven Yeun, Sandra Oh, J.K. Simmons, Robert Kirkman, Simon Racioppa, David Alpert",
                    "Animation, Action, Sci-Fi, Drama",
                    0,
                    "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                    8
            )
            )
            movies.add(
                MovieEntity( 4,
                        "4",
                    "Selena: The Series",
                    7.5,
                    "2020",
                    "As Mexican-American Tejano singer Selena comes of age and realizes her dreams, she and her family make tough choices to hold on to love and music.",
                    "Christian Serratos, Gabriel Chavarria, Ricardo Chavira, Noemi Gonzalez, Seidy López",
                    "Drama",
                    0,
                    "/mYsWyfiIMxx4HDm0Wck7oJ9ckez.jpg",
                    12
            )
            )
            movies.add(
                MovieEntity( 6,
                        "6",
                    "The Good Doctor",
                    8.6,
                    "2017",
                    "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                    "Freddie Highmore, Antonia Thomas, Hill Harper, Richard Schiff",
                    "Drama",
                    0,
                    "/53P8oHo9cfOsgb1cLxBi4pFY0ja.jpg",
                    16
            )
            )
            movies.add(
                MovieEntity( 7,
                        "7",
                    "The Bad Batch",
                    9.0,
                    "2021",
                    "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
                    "Dee Bradley Baker, Michelle Ang",
                    "Animation, Action, Sci-fi",
                    0,
                    "/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
                    8
            )
            )
            movies.add(
                MovieEntity( 8,
                        "8",
                    "The Handmaid's Tale",
                    8.2,
                    "2017",
                    "Set in a dystopian future, a woman is forced to live as a concubine under a fundamentalist theocratic dictatorship. A TV adaptation of Margaret Atwood's novel.",
                    "Elisabeth Moss, Joseph Fiennes, Yvonne StrahovskiMadeline Brewe, Ann Dowd",
                    "Drama, Sci-fi",
                    0,
                    "/oIkxqt6ug5zT5ZSUUyc1Iqopf02.jpg",
                    16
            )
            )
            movies.add(
                MovieEntity(null,
                        "9",
                    "Grey's Anatomy",
                    8.2,
                    "2005",
                    "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                    "Ellen Pompeo, James Pickens Jr., Chandra Wilson",
                    "Drama",
                    0,
                    "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                    16,
                true
            )
            )
            movies.add( MovieEntity(null,
                    "10",
                    "Vincenzo",
                    8.9,
                    "2021",
                    "Vincenzo Cassano is an Italian lawyer and Mafia consigliere who moves back to Korea due to a conflict within his organization. He ends up crossing paths with a sharp-tongued lawyer named Cha-young, and the two join forces in using villainous methods to take down villains who cannot be punished by the law.",
                    "Song Joong-ki, Jeon Yeo-been, Ok Taec-yeon, Kwak Dong-yeon, Kim Yeo-jin",
                    "Action, Comedy",
                    0,
                    "/dvXJgEDQXhL9Ouot2WkBHpQiHGd.jpg",
                    20,
                    true
            )
            )
        }

        return movies
    }

    fun generateEpisode(episodesCount: Int): List<EpisodeEntity>{
        val episodes = ArrayList<EpisodeEntity>()

        for (i in 1..episodesCount){
            episodes.add(
                EpisodeEntity(i,
                        "ep$i",
                    i,
                    "Episode $i",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.")
            )
        }

        return episodes
    }
}
