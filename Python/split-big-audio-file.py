#!/usr/bin/env python3
"""Split big audio file into several small songs according to ChatGPT 4o and o1.

Unverified
"""

import os

# Time markers and track titles
tracks = [
    ("00:00:00", "00:01:33", "I_Am_Astro_Bot"),
    ("00:01:33", "00:02:37", "The_Satellite"),
    ("00:02:37", "00:06:56", "Astro"),
    ("00:06:56", "00:10:11", "Crash_Site"),
    ("00:10:11", "00:16:14", "Cooling_Springs"),
    ("00:16:14", "00:19:53", "Disco_Tree"),
    ("00:19:53", "00:21:46", "Disco_Tree_Miniboss_Remix"),
    ("00:21:46", "00:24:19", "12_Bar_BOT_Funfair_Remix"),
    ("00:24:19", "00:26:42", "Bite_It_Gorilla_Boss"),
    ("00:26:42", "00:30:23", "Spike_Bot"),
    ("00:30:23", "00:32:24", "Specter"),
    ("00:32:24", "00:36:21", "Follow_Me_Into_The_Storm"),
    ("00:36:21", "00:40:25", "Push_The_Boat_Out_Beach_Remix"),
    ("00:40:25", "00:43:31", "Big_Wave"),
    ("00:43:31", "00:45:52", "Tricorn_Escalade"),
    ("00:45:52", "00:48:00", "Tan_Tradicional"),
    ("00:48:00", "00:51:30", "Tite_Mites_Crystal_Cave"),
    ("00:51:30", "00:53:32", "Sad_Seed"),
    ("00:53:32", "01:02:02", "Papa_Tree"),
    ("01:02:02", "01:03:55", "You_Know_What_Youve_Got_to_Do"),
    ("01:03:55", "01:05:58", "Quel_Fromage"),
    ("01:05:58", "01:08:20", "Sucka_Octopus_Boss"),
    ("01:08:20", "01:09:24", "Tread_Lightly"),
    ("01:09:24", "01:13:20", "Bot_of_War"),
    ("01:13:20", "01:14:31", "Villisvin"),
    ("01:14:31", "01:17:00", "Twinkle_Toes"),
    ("01:17:00", "01:18:23", "Retro_Rampage"),
    ("01:18:23", "01:23:19", "Casino_No_Guarantino"),
    ("01:23:19", "01:28:31", "Trapped_In_Time"),
    ("01:28:31", "01:32:00", "Rattled_Snakey_Cave"),
    ("01:32:00", "01:33:33", "Botdi_Beach_Broken_Remix"),
    ("01:33:33", "01:40:48", "Botdi_Beach"),
    ("01:40:48", "01:42:42", "Ninja_Bots_Konnichiwa"),
    ("01:42:42", "01:46:54", "Ninja_Bots"),
    ("01:46:54", "01:49:12", "Hissy_Fit_Snake_Boss"),
    ("01:49:12", "01:54:31", "Drakes_BOTune"),
    ("01:54:31", "01:58:44", "What_Was_That_Scary_Cave_Uncharted_Remix"),
    ("01:58:44", "02:00:11", "I_Havent_Thought_That_Far_Ahead"),
    ("02:00:11", "02:02:25", "Decidedly_Spooky_Miniboss_Remix"),
    ("02:02:25", "02:04:17", "Broken_Bridge"),
    ("02:04:17", "02:07:41", "Pit_of_Peril"),
    ("02:07:41", "02:10:16", "Muteki_Funk"),
    ("02:10:16", "02:15:14", "Marine_Serene"),
    ("02:15:14", "02:18:51", "Push_The_Boat_Out"),
    ("02:18:51", "02:22:46", "Decidedly_Spooky"),
    ("02:22:46", "02:24:42", "Frightfully_Fun"),
    ("02:24:42", "02:26:36", "Invisible_Punk_Chameleon_Boss"),
    ("02:26:36", "02:30:50", "Ninja_Bots_Remix"),
    ("02:30:50", "02:31:57", "Ninja_Bots_Sayonara"),
    ("02:31:57", "02:35:03", "Windswept"),
    ("02:35:03", "02:37:16", "Windswept_Miniboss_Remix"),
    ("02:37:16", "02:39:35", "Trapped_In_Time_Acoustic_Mix"),
    ("02:39:35", "02:43:04", "Cooling_Springs_Chill_Remix"),
    ("02:43:04", "02:45:08", "Freeze_Your_BOT_Off"),
    ("02:45:08", "02:49:46", "You_Cant_Catch_Me_SSD_Powerup_Boss_Remix"),
    ("02:49:46", "02:52:21", "Luna"),
    ("02:52:21", "02:55:17", "Sola"),
    ("02:55:17", "02:59:03", "A_Fire_In_Your_Mind"),
    ("02:59:03", "03:01:35", "Firebooter"),
    ("03:01:35", "03:03:57", "Hatch_A_Plan_Bird_Boss"),
    ("03:03:57", "03:04:32", "Every_Dawn_Dusk"),
    ("03:04:32", "03:08:21", "The_Strength_To_Stand_Alone"),
    ("03:08:21", "03:11:34", "Restore_Power"),
    ("03:11:34", "03:14:31", "Takedown"),
    ("03:14:31", "03:17:48", "Big_Wave_Trash_Remix"),
    ("03:17:48", "03:22:26", "You_Cant_Catch_Me_SSD_Powerup_Boss_Remix"),
    ("03:22:26", "03:25:24", "Crash_Site_Dark_Remix"),
    ("03:25:24", "03:25:45", "The_Launch"),
    ("03:25:45", "03:30:13", "Come_On_Then_Alien_Boss"),
    ("03:30:13", "03:31:02", "Surprise"),
    ("03:31:02", "03:32:49", "Build_a_Bot"),
    ("03:32:49", "03:36:17", "Better_Together"),
    ("03:36:17", "03:39:37", "Astronomical_ASTRO_BOT_Remix"),
    ("03:39:37", "03:42:30", "Fixin"),
    ("03:42:30", "03:44:47", "CPU_Plaza"),
    ("03:44:47", "03:47:14", "Gatcha"),
    ("03:47:14", "03:49:08", "Safari_Park"),
    ("03:49:08", "03:51:27", "Changing_Room"),
    ("03:51:27", "03:54:08", "Dual_Speeder_Garage"),
    ("03:54:08", "03:55:35", "Secret"),
    ("03:55:35", "03:56:46", "Goal_ASTRO_BOT_Remix"),
    ("03:56:46", "03:58:53", "Off_The_Rails"),
    ("03:58:53", "04:00:29", "Perseverance"),
    ("04:00:29", "04:03:45", "Retro_Rampage"),
    ("04:03:45", "04:07:25", "Thats_The_Way_To_Do_It"),
    ("04:07:25", "04:09:16", "Memories_of_Home"),
    ("04:09:16", "04:12:00", "Announce_Trailer"),
]

# Input video file name
input_file = "video.mp4"

# Output folder
output_folder = "tracks"
os.makedirs(output_folder, exist_ok=True)

# Loop through all tracks and split them
for start_time, end_time, title in tracks:
    output_file = os.path.join(output_folder, f"{title}.mp4")
    command = f'ffmpeg -i "{input_file}" -ss {start_time} -to {end_time} -c copy "{output_file}"'
    os.system(command)
    print(f"Processed {title}")

print("All tracks extracted!")
